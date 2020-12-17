import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-will',
  templateUrl: './will.component.html',
  styleUrls: ['./will.component.css']
})
export class WillComponent implements OnInit {
  associate : Associate;

  constructor(private associateService : AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(9).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}
