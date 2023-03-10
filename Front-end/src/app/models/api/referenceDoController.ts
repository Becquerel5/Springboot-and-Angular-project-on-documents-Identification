/**
 * OpenAPI definition
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *//* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';
import { CustomHttpUrlEncodingCodec }                        from '../encoder';

import { Observable }                                        from 'rxjs';

import { Cni } from '../model/cni';
import { Passport } from '../model/passport';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';
import { Referencedocument } from '../model/referencedocument';



@Injectable({
    providedIn: 'root'
  })
export class referenceDoController {

    private baseUrl="http://localhost:8084/api/v1/document";

    constructor(private httpClient: HttpClient) { }

    getCniList(): Observable<Cni[]>{
        return this.httpClient.get<Cni[]>(`${this.baseUrl}/findAll`);
    }
    

    createCni(cni: Cni): Observable<Object>{
        return this.httpClient.post(`${this.baseUrl}/save_cni`,cni);
      }

    getCniById(id: number):Observable<Cni>{
    return this.httpClient.get<Cni>(`${this.baseUrl}/${id}`);
    }

    updateCni(id: number,cni: Cni): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`,cni);
    }

    getAllReferenceDoc(): Observable<Referencedocument[]>{
      return this.httpClient.get<Referencedocument[]>(`${this.baseUrl}/find_All_Documents`);
  }


  createPassport(passport: Passport): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/save_passport`,passport);
  }


  getReferentDocById(idDocumentReference: number):Observable<Referencedocument>{
    return this.httpClient.get<Referencedocument>(`${this.baseUrl}/${idDocumentReference}`);
  }



}
