import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { ViewcatsComponent } from './viewcats/viewcats.component';
import { MycatsComponent } from './mycats/mycats.component';
import { ManageuserComponent } from './manageuser/manageuser.component';
import { AdminModule } from './admin/admin.module';
import { FormsModule } from '@angular/forms';
import { UserService } from './services/user.service';
import { UrlService } from './url.service';
import { CatService } from './services/cat.service';
import { CookieService } from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    ViewcatsComponent,
    MycatsComponent,
    ManageuserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AdminModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    UserService,
    UrlService,
    CatService,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
