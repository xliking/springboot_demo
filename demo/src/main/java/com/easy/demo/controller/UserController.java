package com.easy.demo.controller;

import com.easy.demo.bean.pojo.User;
import com.easy.framework.bean.base.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author muchi
 */
@RestController
@Tag(name = "用户管理", description = "有关用户的操作")
@RequestMapping("/api/users")
public class UserController {

    private final Map<Long, User> userRepository = new HashMap<>();
    private long currentId = 1L;

    @Operation(summary = "获取所有用户", description = "返回所有用户的列表")
    @ApiResponse(responseCode = "200", description = "成功获取用户列表")
    @GetMapping
    public R<List<User>> getAllUsers() {
        return R.success(new ArrayList<>(userRepository.values()));
    }

    @Operation(summary = "创建新用户", description = "根据提供的用户信息创建新用户")
    @ApiResponse(responseCode = "200", description = "用户创建成功")
    @PostMapping
    public R<User> createUser(@RequestBody User user) {
        user.setId(currentId++);
        userRepository.put(user.getId(), user);
        return R.success(user);
    }

    @Operation(summary = "获取用户详情", description = "根据用户ID获取用户详细信息")
    @ApiResponse(responseCode = "200", description = "成功获取用户信息")
    @ApiResponse(responseCode = "500", description = "未找到用户")
    @GetMapping("/{id}")
    public R<User> getUserById(@PathVariable Long id) {
        User user = userRepository.get(id);
        if (user != null) {
            return R.success(user);
        }
        return R.fail("未找到用户");
    }

    @Operation(summary = "更新用户信息", description = "根据用户ID更新用户信息")
    @ApiResponse(responseCode = "200", description = "用户更新成功")
    @ApiResponse(responseCode = "500", description = "未找到用户")
    @PutMapping("/{id}")
    public R<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userRepository.get(id);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            userRepository.put(id, user);
            return R.success(user);
        }
        return R.fail("未找到用户");
    }

    @Operation(summary = "删除用户", description = "根据用户ID删除用户")
    @ApiResponse(responseCode = "200", description = "用户删除成功")
    @ApiResponse(responseCode = "500", description = "未找到用户")
    @DeleteMapping("/{id}")
    public R<String> deleteUser(@PathVariable Long id) {
        if (userRepository.containsKey(id)) {
            userRepository.remove(id);
            return R.success("用户删除成功");
        }
        return R.fail("未找到用户");
    }
}
