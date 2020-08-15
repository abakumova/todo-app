package com.todo.tests.model;

import com.todo.model.dto.todo.TodoDto;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;
import com.todo.tests.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TodoTest {

    private TodoDto todoDto = new TodoDto();
    private MockMvc mockMvc;

    @Spy
    @InjectMocks
    private TodoService todoService;

    @Autowired
    private WebApplicationContext context;

    @Mock
    private TodoRepository todoRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
    }

    @Test
    void verifyGetTodos() throws Exception {
        mockMvc.perform(get("/ta/apis/v1/todos"))
                .andExpect(status().isOk());
    }

    @Test
    void verifyPostTodo() throws Exception {
        //given
        todoDto.setTitle("Test");

        //when
        mockMvc.perform(
                post("/ta/apis/v1/todos")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.asJsonString(todoDto)))
                .andExpect(status().isOk());
    }
}