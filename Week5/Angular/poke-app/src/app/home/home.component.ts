import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../models/pokemon';
import { PokemonService } from '../pokemon.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  id: number;
  homePokemon: Pokemon;

  constructor(private pokemonService: PokemonService) { }

  ngOnInit(): void {
    this.id = 1;
    this.getPokemon();
  }

  getPokemon() {
    this.pokemonService.getPokemonById(this.id).subscribe(
      resp => {
        this.homePokemon = resp;
      }
    );
  }

}
