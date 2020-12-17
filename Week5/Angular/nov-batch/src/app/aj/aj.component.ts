import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-aj',
  templateUrl: './aj.component.html',
  styleUrls: ['./aj.component.css']
})
export class AJComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(8675309).subscribe(
      resp => {
        this.associate = resp;
      }
    )
  }

}
