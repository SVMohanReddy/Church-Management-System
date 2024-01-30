package com.project.controller.membercontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.entity.GroupMember;
import com.project.service.GroupMemberService;

@RestController
@RequestMapping("/groupMember")
public class GroupMemberController {

    @Autowired
    private GroupMemberService groupMemberService;

    @PostMapping("/create/{groupId}")
    public ResponseEntity<Object> createMembersForGroup(@PathVariable Long groupId, @RequestBody GroupMember groupMember) {
        try {
            ResponseEntity<List<GroupMember>> response = groupMemberService.createMembersForGroup(groupId, groupMember);
            if (response.getStatusCode() == HttpStatus.CREATED) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group not found with groupId: " + groupId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to create GroupMember.");
        }
    }

    @GetMapping("/getAllMembers")
    public ResponseEntity<Object> getAllGroupMembers() {
        try {
            List<GroupMember> groupMembers = groupMemberService.getAllGroupMembers();
            return ResponseEntity.ok(groupMembers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to retrieve group members.");
        }
    }

    @GetMapping("/getMemberById/{groupMemberId}")
    public ResponseEntity<Object> getGroupMemberById(@PathVariable Long groupMemberId) {
        try {
            ResponseEntity<GroupMember> response = groupMemberService.getGroupMemberById(groupMemberId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group member not found with groupMemberId: " + groupMemberId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to retrieve group member.");
        }
    }

    @PutMapping("/updateMemberById/{groupMemberId}")
    public ResponseEntity<Object> updateGroupMember(@PathVariable Long groupMemberId, @RequestBody GroupMember updatedGroupMember) {
        try {
            ResponseEntity<GroupMember> response = groupMemberService.updateGroupMember(groupMemberId, updatedGroupMember);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group member not found with groupMemberId: " + groupMemberId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to update group member.");
        }
    }

    @DeleteMapping("/deleteMemberById/{groupMemberId}")
    public ResponseEntity<Object> deleteGroupMember(@PathVariable Long groupMemberId) {
        try {
            ResponseEntity<String> response = groupMemberService.deleteGroupMember(groupMemberId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group member not found with groupMemberId: " + groupMemberId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to delete group member.");
        }
    }
}
