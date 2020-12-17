import { Component, OnInit } from '@angular/core';
import { Associate } from '../classes/associate';
import AssociateService from '../associate.service';

@Component({
  selector: 'app-aj',
  templateUrl: './aj.component.html',
  styleUrls: ['./aj.component.css']
})
export class AJComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(1).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}
