import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-david',
  templateUrl: './david.component.html',
  styleUrls: ['./david.component.css']
})
export class DavidComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(15).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}
