import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-spencer',
  templateUrl: './spencer.component.html',
  styleUrls: ['./spencer.component.css']
})
export class SpencerComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(6).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}
