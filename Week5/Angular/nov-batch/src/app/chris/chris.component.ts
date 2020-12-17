import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-chris',
  templateUrl: './chris.component.html',
  styleUrls: ['./chris.component.css']
})
export class ChrisComponent implements OnInit {
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