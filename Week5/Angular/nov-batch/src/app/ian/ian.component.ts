import { Component, OnInit } from '@angular/core';
import { Associate } from '../classes/associate';
import AssociateService from '../associate.service';

@Component({
  selector: 'app-ian',
  templateUrl: './ian.component.html',
  styleUrls: ['./ian.component.css']
})
export class IanComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(12).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}
