import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddcatComponent } from './addcat/addcat.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [AddcatComponent],
  imports: [
    CommonModule,
    FormsModule
  ],
  bootstrap: [AddcatComponent]
})
export class AdminModule { }
