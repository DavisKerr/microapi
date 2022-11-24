package com.davis.microapi.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.microapi.model.Composite;
import com.davis.microapi.model.Goal;
import com.davis.microapi.model.User;
import com.davis.microapi.repository.GoalRepository;
import com.davis.microapi.repository.ProgressRepository;
import com.davis.microapi.repository.UserRepository;
import com.davis.microapi.model.Progress;

@RestController
@RequestMapping(path = "/goals")
public class GoalController {

    final GoalRepository goalRepository;
    final UserRepository userRepository;
    final ProgressRepository progressRepository;

    @Autowired
    public GoalController(GoalRepository goalRepository, UserRepository userRepository, ProgressRepository progressRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
        this.progressRepository = progressRepository;
    }


    public List<Goal> getGoals() {
        Long id = getUser();
        return goalRepository.findByUserId(id);
    }

    public List<Progress> getProgress() {
        Long id = getUser();
        return progressRepository.findByUserId(id);
    }

    private List<Progress> flatten(List<List<Progress>> o) {
        List<Progress> ls = new ArrayList<>();
        o.forEach(ls::addAll);
        return ls;

    }

    @PostMapping
    public List<Object> sync(@RequestBody List<Composite> userData) {
        List<Goal> userGoals = userData.stream().map(comp -> comp.getGoal()).toList();
        List<Progress> userProgress = flatten(userData.stream().map(comp -> comp.getProgress()).toList());
        Queue<Composite> queue = new LinkedList<>(userData);
        doDBUpdates(queue, getUser());
        List<Goal> apiGoals = getGoals();
        List<Progress> apiProgresses = getProgress();
        return genDBUpdates(apiGoals, apiProgresses, userGoals, userProgress, getUser());
        //List<String> uuidExemptList = userGoals.stream().map(goal -> goal.getUuid()).toList();
        //return genDBUpdates(apiGoals, uuidExemptList, getUser());
    }

    private List<Object> genDBUpdates(
        List<Goal> apiGoals, 
        List<Progress> apiProgress, 
        List<Goal> userGoals, 
        List<Progress> userProgress, 
        Long userId
    ) {
        List<Object> stuffToUpdate = apiGoals.stream().filter(goal -> !userGoals.contains(goal)).collect(Collectors.toList());
        List<Object> moreStuffToUpdate = apiProgress.stream().filter(progress -> !userProgress.contains(progress)).collect(Collectors.toList());
        stuffToUpdate.addAll(moreStuffToUpdate);
        return stuffToUpdate;
    }

    private void doDBUpdates(Queue<Composite> queue, Long userId) {
        
        while(!queue.isEmpty()) { 
            Composite comp = queue.remove();
            Goal goal = comp.getGoal();
            List<Progress> progress = comp.getProgress();
            Optional<Goal> oldGoal = goalRepository.findByUuidAndUserId(goal.getUuid(), userId);
            if(oldGoal.isPresent()) {
                Goal toUpdate = oldGoal.orElse(new Goal());
                System.out.println(toUpdate.equals(goal));
                goalRepository.setGoalByUuid(
                    goal.getName(),
                    goal.getVerb(),
                    goal.getMeasurement(),
                    goal.getQuantity(),
                    goal.getPeriod(),
                    goal.getStartDate(),
                    goal.getEndDate(),
                    goal.getCompleted(), 
                    goal.getDateCreated(),
                    goal.getIsTestData(),
                    goal.getDeleted(), 
                    toUpdate.getUuid()
                );

            }
            else {
                goal.setUser(userRepository.findById(userId).orElse(null));
                goalRepository.save(goal);
            }

            for(Progress event:progress) {
                Optional<Progress> oldEvent = progressRepository.findByUuidAndUserId(event.getUuid(), userId);
                if(oldEvent.isPresent()) {
                    Progress toUpdate = oldEvent.orElse(new Progress());
                    progressRepository.setProgressByUuid(
                        event.getUnits(),
                        event.getTestData(),
                        event.getDeleted(),
                        toUpdate.getUuid()
                    );
                }
                else {
                    event.setUser(userRepository.findById(userId).orElse(null));
                    progressRepository.save(event);
                }
            }
        }
        
    }

    private Long getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User me = userRepository.findByUsername(currentPrincipalName).orElseThrow();
        return me.getId();
	}
    
    
}


// [
//     {
//         "goal" : {
//             "uuid": "UUIDs",
//             "name": "Goal8",
//             "verb": "vese",
//             "measurement": "null",
//             "quantity": 75.0,
//             "period": 2,
//             "startDate": "null",
//             "endDate": "null",
//             "completed": false,
//             "dateCreated": "null",
//             "isTestData": false,
//             "deleted": false
//         },
//         "progress" : [
//             {
//                 "goalUuid" : "UUIDS",
//                 "uuid" : "PROGRESS",
//                 "units" : 7,
//                 "testData" : false,
//                 "deleted" : false
//             }
//         ]
//     }
// ]