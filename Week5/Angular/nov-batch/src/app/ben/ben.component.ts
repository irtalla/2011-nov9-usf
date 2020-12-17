import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-ben',
  templateUrl: './ben.component.html',
  styleUrls: ['./ben.component.css']
})
export class BenComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(11).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}
