import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-henry',
  templateUrl: './henry.component.html',
  styleUrls: ['./henry.component.css']
})
export class HenryComponent implements OnInit {

  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(17).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }
}
