import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-kyle',
  templateUrl: './kyle.component.html',
  styleUrls: ['./kyle.component.css']
})
export class KyleComponent implements OnInit {
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