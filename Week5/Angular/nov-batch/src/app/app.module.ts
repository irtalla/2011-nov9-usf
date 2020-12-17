import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SierraComponent } from './sierra/sierra.component';
import { ShakerComponent } from './shaker/shaker.component';
import { HenryComponent } from './henry/henry.component';
import { ChrisComponent } from './chris/chris.component';
import { BobbyComponent } from './bobby/bobby.component';
import { MuhammadComponent } from './muhammad/muhammad.component';

@NgModule({
  declarations: [
    AppComponent,
    SierraComponent,
    HenryComponent,
    ChrisComponent,
    ShakerComponent,
    BobbyComponent,
    MuhammadComponent
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
