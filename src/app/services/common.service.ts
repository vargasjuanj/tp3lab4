import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export abstract class CommonService<E> {

  protected miUrl: string;

  constructor(protected http: HttpClient) { }

  getOne( id:number): Observable<E[]> {
    return this.http.get<E[]>(this.miUrl+id);
  }

  getAll(id: number): Observable<E[][]> {
    return this.http.get<E[][]>(this.miUrl);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(this.miUrl + id);
  }

  post(entity: E): Observable<E> {
    return this.http.post<E>(this.miUrl, entity);
  }

  put(id: number, entity: E) {
    return this.http.put<E>(this.miUrl + id, entity);
  }
}
