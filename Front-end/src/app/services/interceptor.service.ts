import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { AuthentificationService } from './authentification.service';
import * as constant from './constants';
@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {


  constructor(
    private authenticationService: AuthentificationService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.authenticationService.getToken() !=constant.tokenDefaultValue ) {
        request = request.clone({
          setHeaders: { Authorization: this.authenticationService.getToken() }
        });
    }
    return next.handle(request).pipe(
      map((event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
            if(event.headers.has(constant.headerAuthori) != null ) {
              this.authenticationService.saveToken(event.headers.get(constant.headerAuthori)!)
            }
          }
          if (event instanceof HttpErrorResponse) {
            if(event.headers.has(constant.headerAuthori) != null ) {
              this.authenticationService.saveToken(event.headers.get(constant.headerAuthori)!)
            }
          }
          return event;
        }
      )
    );
  }
}

