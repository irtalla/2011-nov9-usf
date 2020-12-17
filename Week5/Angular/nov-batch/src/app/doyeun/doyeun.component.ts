import { Component, OnInit } from '@angular/core';
import AssociateService from "../associate.service"
import {Associate} from "../classes/associate"

@Component({
  selector: 'app-doyeun',
  templateUrl: './doyeun.component.html',
  styleUrls: ['./doyeun.component.css']
})
export class DoyeunComponent implements OnInit {
  associate: Associate;

  constructor(private associateService: AssociateService) { }

  ngOnInit(): void {
    this.associateService.getAssociateById(13).subscribe(
      resp => {
        this.associate = resp;
      }
    )
  }

}
