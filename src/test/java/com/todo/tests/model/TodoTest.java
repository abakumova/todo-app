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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TodoTest {

    private final TodoDto todoDto = new TodoDto();
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
        todoDto.setCreatedAt(new Date(System.currentTimeMillis()));
        todoDto.setIsCompleted(false);

        //when
        mockMvc.perform(
                post("/ta/apis/v1/todos")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.asJsonString(todoDto)))
                .andExpect(status().isOk());
    }

    @Test
    void verifyDeleteTodo() throws Exception {
        //given
        todoDto.setId("test000111");
        todoDto.setTitle("Test");

        mockMvc.perform(
                post("/ta/apis/v1/todos")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.asJsonString(todoDto)))
                .andExpect(status().isOk());

        //when
        mockMvc.perform(
                delete("/ta/apis/v1/todos/{id}", "test000111")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}