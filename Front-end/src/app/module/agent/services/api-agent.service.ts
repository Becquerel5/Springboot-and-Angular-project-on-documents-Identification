import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Agent, Cni, LoginBean } from 'src/app/models';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiAgentService {


  private domain = environment.basePath;

  agent!: Agent;

  constructor(private http : HttpClient) { }

  public image(email: string): Observable<HttpResponse<any>> {
    const uri = "/api/v1/file/get_image";
    const headers = new HttpHeaders();
    let params = new HttpParams().append("path", email);
    return this.sendRequest<any>('get', this.domain + uri, headers, params, null);
  }

  public getDocument(status: Cni.StatusTreatmentSystemeListEnum, page: number, size: number): Observable<HttpResponse<any>> {
    const uri = "/api/v1/document/find_All_document_by_page";
    const headers = new HttpHeaders();
    let params = new HttpParams().append("status", status).append("size", size).append("page", page);
    return this.sendRequest<any>('get', this.domain + uri, headers, params, null);
  }

  public getDocumentBuild(status: Cni.StatusInTreatmentEnum, page: number, size: number): Observable<HttpResponse<any>> {
    const uri = "/api/v1/document/find_All_document_by_page";
    const headers = new HttpHeaders();
    let params = new HttpParams().append("status", status).append("size", size).append("page", page);
    return this.sendRequest<any>('get', this.domain + uri, headers, params, null);
  }

  public connectClient(email: string): Observable<HttpResponse<any>> {
    const uri = "/login";
    const headers = new HttpHeaders();
    let params = new HttpParams().append("login", email);
    return this.sendRequest<any>('get', this.domain + uri, headers, params, null);
  }

  public connectAgent(loginBean: LoginBean): Observable<HttpResponse<any>> {
    const uri = "/login";
    const headers = new HttpHeaders();
    let params = new HttpParams();
    return this.sendRequest<any>('post', this.domain + uri, headers, params, loginBean);
  }

  private sendRequest<T>(
    method: string,
    url: string,
    headers: HttpHeaders,
    params: HttpParams,
    body: any): Observable<HttpResponse<T>> {
    let requestObservable!: Observable<HttpResponse<T>>;
    if (method === 'get') {
      requestObservable = this.http.get<T>(
        url,
        {
          headers: headers.set('Accept', 'application/json'),
          params: params,
          observe: 'response'
        }
      );
    } else if (method === 'put') {
      requestObservable = this.http.put<T>(
        url,
        body,
        {
          headers: headers.set('Content-Type', 'application/json'),
          params: params,
          observe: 'response'
        }
      );
    } else if (method === 'post') {
      requestObservable = this.http.post<T>(
        url,
        body,
        {
          headers: headers.set('Content-Type', 'application/json'),
          params: params,
          observe: 'response'
        }
      );
    } else if (method === 'delete') {
      requestObservable = this.http.delete<T>(
        url,
        {
          headers: headers,
          params: params,
          observe: 'response'
        }
      );
    } /*else {
      return Observable.throw('Unsupported request: ' + method);
    }*/
    return requestObservable;
  }
}
