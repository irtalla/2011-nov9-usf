import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-shaker',
  templateUrl: './shaker.component.html',
  styleUrls: ['./shaker.component.css']
})
export class ShakerComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(8).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}
