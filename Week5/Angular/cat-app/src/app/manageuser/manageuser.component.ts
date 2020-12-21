import { Component, OnInit } from '@angular/core';
import { Person } from '../models/person';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-manageuser',
  templateUrl: './manageuser.component.html',
  styleUrls: ['./manageuser.component.css']
})
export class ManageuserComponent implements OnInit {
  loggedPerson: Person;
  newUsername: string;
  newPassword: string;
  newPassword2: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.loginUser(null,null).subscribe(
      resp => {
        this.loggedPerson = resp;
      }
    );
  }

  updateUsername() {
    if (this.newUsername) {
      this.loggedPerson.username = this.newUsername;
      this.userService.updateUser(this.loggedPerson).subscribe();
    } else {
      alert('You didn\'t enter a username.');
    }
  }

  updatePassword() {
    if (this.newPassword) {
      if (this.newPassword === this.newPassword2) {
        this.loggedPerson.password = this.newPassword;
        this.userService.updateUser(this.loggedPerson).subscribe();
      } else {
        alert('The passwords don\'t match.');
        this.newPassword = '';
        this.newPassword2 = '';
      }
    } else {
      alert('You didn\'t enter a password.');
    }
  }

}
