import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-sierra',
  templateUrl: './sierra.component.html',
  styleUrls: ['./sierra.component.css']
})
export class SierraComponent implements OnInit {
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
