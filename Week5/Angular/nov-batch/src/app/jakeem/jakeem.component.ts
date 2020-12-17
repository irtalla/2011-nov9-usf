import { Component, OnInit } from '@angular/core';
import AssociateService from '../associate.service';
import { Associate } from '../classes/associate';

@Component({
  selector: 'app-jakeem',
  templateUrl: './jakeem.component.html',
  styleUrls: ['./jakeem.component.css']
})
export class JakeemComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(13).subscribe(
      resp => {
        this.associate = resp;
      }
    );
  }

}
