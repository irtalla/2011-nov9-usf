import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SierraComponent } from './sierra/sierra.component';
import { BenComponent } from './ben/ben.component';
import { ShakerComponent } from './shaker/shaker.component';
import { HenryComponent } from './henry/henry.component';
import { ChrisComponent } from './chris/chris.component';
import { BobbyComponent } from './bobby/bobby.component';
import { MuhammadComponent } from './muhammad/muhammad.component';
import { SpencerComponent } from './spencer/spencer.component';
import { KyleComponent } from './kyle/kyle.component';
import AssociateService from './associate.service';
import { AJComponent } from './aj/aj.component';
import { WillComponent } from './will/will.component';
import { IanComponent } from './ian/ian.component';
import { JakeemComponent } from './jakeem/jakeem.component';
import { JasonComponent } from './jason/jason.component';
import { DavidComponent } from './david/david.component';

@NgModule({
  declarations: [
    AppComponent,
    SierraComponent,
    BenComponent,
    HenryComponent,
    ChrisComponent,
    ShakerComponent,
    BobbyComponent,
    MuhammadComponent,
    SpencerComponent,
    KyleComponent,
    AJComponent,
    WillComponent,
    IanComponent,
    JakeemComponent,
    JasonComponent,
    DavidComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    AssociateService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
