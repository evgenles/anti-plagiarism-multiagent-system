package com.diit.antiplagitarism.webadmin;

import com.diit.antiplagitarism.socket.models.AddTaskDto;
import com.diit.antiplagitarism.webadmin.models.Task;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class TasksController {


    @GetMapping("/tasks/list")
    public ResponseEntity<?> getTasks(Model model) {

        return ResponseEntity.ok(StaticHelper.tasks.values());
    }


    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTasks(@PathVariable String id) {
        return ResponseEntity.ok(StaticHelper.tasks.get(UUID.fromString(id)));
    }

    @PostMapping(value = "/tasks/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity addNewTask(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "analyzers", required = true) List<String> analyzers,
            @RequestParam(value = "priority", required = true) Boolean priority,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        AddTaskDto dto = new AddTaskDto(name, priority, analyzers, file.getBytes(), UUID.randomUUID());
        StaticHelper.tasks.put(dto.getTaskId(), new Task(dto.getTaskId(),name, false, 0.0, 100.0));
        StaticHelper.communicator.AddTask(dto);
        return ResponseEntity.ok().build();
    }
}
