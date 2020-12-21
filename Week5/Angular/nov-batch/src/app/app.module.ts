import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SierraComponent } from './sierra/sierra.component';
import { HenryComponent } from './henry/henry.component';
import AssociateService from './associate.service';

@NgModule({
  declarations: [
    AppComponent,
    SierraComponent,
    HenryComponent
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
