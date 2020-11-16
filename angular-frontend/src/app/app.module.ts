import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import {AppComponent} from './app.component';

import { TodoListComponent } from './todo-list.component';
import { TodoService } from './todo.service';
import {NgGroupByPipeModule} from "./group-by.pipe";

@NgModule({
  declarations: [
    AppComponent,
    TodoListComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    NgGroupByPipeModule
  ],
  providers: [TodoService],
  bootstrap: [AppComponent]
})
export class AppModule {

}
