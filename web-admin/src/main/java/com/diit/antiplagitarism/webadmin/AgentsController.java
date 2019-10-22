package com.diit.antiplagitarism.webadmin;

import com.diit.antiplagitarism.socket.models.AddTaskDto;
import com.diit.antiplagitarism.webadmin.StaticHelper;
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
import java.util.List;
import java.util.UUID;

@Controller
public class AgentsController {

    @GetMapping("/agents/list")
    public ResponseEntity<?> getAgents(Model model) {

        return ResponseEntity.ok(StaticHelper.agents.values());
    }

}
