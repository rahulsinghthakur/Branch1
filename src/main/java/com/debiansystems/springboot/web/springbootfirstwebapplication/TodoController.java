package com.debiansystems.springboot.web.springbootfirstwebapplication;


import com.debiansystems.springboot.web.springbootfirstwebapplication.model.Todo;
import com.debiansystems.springboot.web.springbootfirstwebapplication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService service;

    @InitBinder
    public void initBinder(WebDataBinder binder){
    //To use date format as DD/MM/YYYY
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));

    }


    @RequestMapping(value="/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model){
        String name = getLoggedInUserName(model);
        model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }

    private String getLoggedInUserName(ModelMap model) {
        return (String) model.get("name");
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model){
        model.addAttribute("todo",new Todo(0,(String) model.get("name"),"Default Desc",new Date(),false));
        return "todo";
    }

    @RequestMapping(value="/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id){
        if(id==1)
            throw new RuntimeException("Something Went wrong!!!");
        service.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id , ModelMap model){
        Todo todo = service.retrieveTodos(id);
        model.put("todo",todo);
        return "todo";
    }

    @RequestMapping(value="/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model , @Valid Todo todo ,BindingResult result){

        if(result.hasErrors()){
            return "todo";
        }
        todo.setUser((String) model.get("name"));
        service.updateTodo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        service.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(), false);
        return "redirect:/list-todos";
    }

}
