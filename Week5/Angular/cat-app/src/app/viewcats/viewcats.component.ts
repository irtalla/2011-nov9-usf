import { Component, OnInit } from '@angular/core';
import { Person } from '../models/person';
import { Cat } from '../models/cat';
import { CatService } from '../services/cat.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-viewcats',
  templateUrl: './viewcats.component.html',
  styleUrls: ['./viewcats.component.css']
})
export class ViewcatsComponent implements OnInit {
  loggedPerson: Person;
  availableCats: Cat[];

  constructor(private personService: UserService, private catService: CatService) { }

  ngOnInit(): void {
    this.personService.loginUser(null,null).subscribe(
      resp => {
        this.loggedPerson = resp;
      }
    );
    this.catService.getAvailableCats().subscribe(
      resp => {
        this.availableCats = resp;
      }
    );
  }

  adoptCat(id: number) {
    this.catService.adoptCat(id).subscribe();
  }

}
