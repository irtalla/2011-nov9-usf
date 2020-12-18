import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SierraComponent } from './sierra/sierra.component';
<<<<<<< HEAD
import { BobbyComponent } from './bobby/bobby.component';
=======
import AssociateService from './associate.service';
>>>>>>> 12d9aec89d42c2f81c1f095ef017d235d7e393ad

@NgModule({
  declarations: [
    AppComponent,
    SierraComponent,
    BobbyComponent
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
