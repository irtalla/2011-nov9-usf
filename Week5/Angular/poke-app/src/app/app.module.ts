import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PokemonComponent } from './pokemon/pokemon.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { MoveFilterPipe } from './move-filter.pipe';
import { TypeColorDirective } from './type-color.directive';

@NgModule({
  declarations: [
    AppComponent,
    PokemonComponent,
    HomeComponent,
    MoveFilterPipe,
    TypeColorDirective
  ],
  imports: [
    BrowserModule,
    FormsModule, // allows us to use ngModel (two-way binding)
    HttpClientModule // allows us to use HttpClient
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
