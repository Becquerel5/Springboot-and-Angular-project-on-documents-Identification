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

import { Passport } from '../model/passport';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable({
    providedIn: 'root'
  })
export class PassportControllerService {

    //private baseUrl="http://localhost:8084/api/v1/passport";
    private baseUrl="http://localhost:8084/api/v1/cni";

    constructor(private httpClient: HttpClient) { }

    createPassport(passport: Passport): Observable<Object>{
        return this.httpClient.post(`${this.baseUrl}/savepassport`,passport);
      }

    

}
