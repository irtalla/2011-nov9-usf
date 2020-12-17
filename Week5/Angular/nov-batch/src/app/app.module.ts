import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SierraComponent } from './sierra/sierra.component';
import { DoyeunComponent } from './doyeun/doyeun.component';

@NgModule({
  declarations: [
    AppComponent,
    SierraComponent,
    DoyeunComponent
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
