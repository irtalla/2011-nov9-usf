import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SierraComponent } from './sierra/sierra.component';
<<<<<<< HEAD
import { ShakerComponent } from './shaker/shaker.component';
=======
import { HenryComponent } from './henry/henry.component';
>>>>>>> a43dee970c3eb9d754908b950dc2dd3376dffaf9

@NgModule({
  declarations: [
    AppComponent,
    SierraComponent,
<<<<<<< HEAD
    ShakerComponent
=======
    HenryComponent
>>>>>>> a43dee970c3eb9d754908b950dc2dd3376dffaf9
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
