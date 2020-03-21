import { Component, OnInit } from '@angular/core';
import { Pais } from 'src/app/models/pais';
import { PaisService } from 'src/app/services/Pais';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
public paises:Pais[][]=[]
  constructor(private paisService: PaisService) { 
   
  }
  ngOnInit() {
  this.getAll()
  }
  getAll() {
  for (let id=1; id<=300; id++){
    this.paisService.getOne(id).subscribe( res => {
      this.paises.push(res);
    });

  }

    

  }


}
