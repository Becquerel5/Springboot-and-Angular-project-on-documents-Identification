import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginBean } from 'src/app/models';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiClientService {


  private domain = environment.basePath;

  public idClient!: number;

  public emailClient!: string;

  constructor(private http : HttpClient) { }

  getTenFirstCharacter(value: string){
    if(value?.length>9)
      return value.slice(0,10);
    else
      return value;
  }

  geCharacter(value: string){
    if(value.length>7)
      return value.slice(0,8)+' ...';
    else
      return value;
  }

  public getCurrentIssue(id: number): Observable<HttpResponse<any>> {
    const uri = "/api/v1/document/find_current_document_by_id/"+id;
    const headers = new HttpHeaders();
    let params = new HttpParams();
    return this.sendRequest<any>('get', this.domain + uri, headers, params, null);
  }

  public getCurrentIssueClient(id: number): Observable<HttpResponse<any>> {
    const uri = "/api/v1/document/find_current_document_by_id_client/"+id;
    const headers = new HttpHeaders();
    let params = new HttpParams();
    return this.sendRequest<any>('get', this.domain + uri, headers, params, null);
  }

  public initCni(id: number): Observable<HttpResponse<any>> {
    const uri = "/api/v1/document/init_cni/"+id;
    const headers = new HttpHeaders();
    let params = new HttpParams();
    return this.sendRequest<any>('get', this.domain + uri, headers, params, null);
  }

  public initPassPort(id: number): Observable<HttpResponse<any>> {
    const uri = "/api/v1/document/init_passPort/"+id;
    const headers = new HttpHeaders();
    let params = new HttpParams();
    return this.sendRequest<any>('get', this.domain + uri, headers, params, null);
  }

  public saveCni(cni: any): Observable<HttpResponse<any>> {
    const uri = "/api/v1/document/save_cni";
    const headers = new HttpHeaders();
    let params = new HttpParams();
    return this.sendRequest<any>('post', this.domain + uri, headers, params, cni);
  }

  public savePassPort(passPort: any): Observable<HttpResponse<any>> {
    const uri = "/api/v1/document/save_passport";
    const headers = new HttpHeaders();
    let params = new HttpParams();
    return this.sendRequest<any>('post', this.domain + uri, headers, params, passPort);
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
