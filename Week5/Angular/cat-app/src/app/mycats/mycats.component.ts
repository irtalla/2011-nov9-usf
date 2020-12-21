import { Component, OnInit } from '@angular/core';
import { Person } from '../models/person';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-mycats',
  templateUrl: './mycats.component.html',
  styleUrls: ['./mycats.component.css']
})
export class MycatsComponent implements OnInit {
  loggedUser: Person;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.loginUser(null,null).subscribe(
      resp => {
        this.loggedUser = resp;
      }
    );
  }

}
