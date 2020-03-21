import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Pais } from '../models/pais';

@Injectable({
  providedIn: 'root'
})
export class PaisService extends CommonService<Pais> {


  protected miUrl = 'https://restcountries.eu/rest/v2/callingcode/';



}
