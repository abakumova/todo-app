package com.todo.tests.model;

import com.todo.model.dto.todo.TodoDto;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TodoTest {

    private TodoDto todoDto = new TodoDto();
    private MockMvc mvc;

    @Spy
    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    void verifyGetTodos() throws Exception {
        mvc.perform(get("/ta/apis/v1/todos"))
                .andExpect(status().isOk());
    }
}