import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-lydia',
  templateUrl: './lydia.component.html',
  styleUrls: ['./lydia.component.css']
})
export class LydiaComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(16).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}
