package pl.wrryy.amelco.controller;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.assertj.core.api.Assertions.assertThat;


import pl.wrryy.amelco.entity.Message;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.service.MessageService;
import pl.wrryy.amelco.service.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private MessageService messageService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        List<Message> messages = Arrays.asList(new Message(1l, new User(), new User(), "mess1", LocalDateTime.now()), new Message(2l, new User(), new User(), "mess2", LocalDateTime.now()));
        when(this.messageService.getConversationWithUser(new User(), new User())).thenReturn(messages);
    }

    @Test
    public void allConversations() throws Exception {
        assertThat(this.userService).isNotNull();
        mockMvc.perform(get("/user/messages")).andExpect(model().attributeExists("messages"))
                .andExpect(model().attribute("messages", hasSize(2)))
                .andExpect(model().attribute("messages", hasItem(anyOf(hasProperty("text"), is("mess1")))));
    }
    @Test
    public void walletDeposit() {
    }
    @Test
    public void makeBet() {
    }
}