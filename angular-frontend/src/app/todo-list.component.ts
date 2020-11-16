import {Component, Input, OnInit} from '@angular/core';
import {TodoService} from './todo.service';
import {Todo} from './todo';
import {NgForm} from '@angular/forms';
import {printLine} from "tslint/lib/test/lines";
import {element} from "protractor";


@Component({
  // tslint:disable-next-line:component-selector
  selector: 'todo-list',
  templateUrl: './todo-list.component.html'
})

export class TodoListComponent implements OnInit {

  todos: Todo[] = [];
  newTodo: Todo = new Todo();
  editing = false;
  editingTodo: Todo = new Todo();

  constructor(
    private todoService: TodoService,
  ) {
  }

  ngOnInit(): void {
    this.getTodos();
  }

  getTodos(): void {
    this.todoService.getTodos()
      .then(todos => this.todos = todos);
  }

  createTodo(todoForm: NgForm): void {
    this.todoService.createTodo(this.newTodo)
      .then(createTodo => {
        todoForm.reset();
        this.newTodo = new Todo();
        this.todos.unshift(createTodo)
      });
  }

  deleteTodo(id: string): void {
    this.todoService.deleteTodo(id)
      .then(() => {
        // tslint:disable-next-line:triple-equals
        this.todos = this.todos.filter(todo => todo.id != id);
      });
  }

  updateTodo(todoData: Todo): void {
    console.log(todoData);
    this.todoService.updateTodo(todoData)
      .then(updatedTodo => {
        const existingTodo = this.todos.find(todo => todo.id === updatedTodo.id);
        Object.assign(existingTodo, updatedTodo);
        this.clearEditing();
      });
  }

  toggleIsCompleted(todoData: Todo): void {
    todoData.isCompleted = !todoData.isCompleted;
    this.todoService.updateTodo(todoData)
      .then(updatedTodo => {
        const existingTodo = this.todos.find(todo => todo.id === updatedTodo.id);
        Object.assign(existingTodo, updatedTodo);
      });
  }

  editTodo(todoData: Todo): void {
    this.editing = true;
    Object.assign(this.editingTodo, todoData);
  }

  clearEditing(): void {
    this.editingTodo = new Todo();
    this.editing = false;
  }
}
