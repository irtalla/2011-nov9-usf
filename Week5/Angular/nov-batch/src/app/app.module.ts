import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SierraComponent } from './sierra/sierra.component';
<<<<<<< HEAD
import { LydiaComponent } from './lydia/lydia.component';
=======
import AssociateService from './associate.service';
>>>>>>> 7bc626fa3544237d3c1bfd3a8d981d06d92657b6

@NgModule({
  declarations: [
    AppComponent,
    SierraComponent,
    LydiaComponent
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
