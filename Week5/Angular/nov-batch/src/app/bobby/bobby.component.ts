import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-bobby',
  templateUrl: './bobby.component.html',
  styleUrls: ['./bobby.component.css']
})
export class BobbyComponent implements OnInit {
  associate: Associate;
  constructor(private associateService: AssociateService) { }


  ngOnInit(): void {
    this.associateService.getAssociateById(2).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}