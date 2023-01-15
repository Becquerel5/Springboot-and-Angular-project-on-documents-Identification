import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalDaoAgentService {

  constructor() { }

  private getDataAsJSON (key: string, removeItem: boolean = false): any {
    return JSON.parse(this.getData(key, removeItem));
  }
  save (key: string, data: any, force: boolean = true, stringify: boolean = true): boolean {
    if (sessionStorage.getItem(key) != null && !force) {
      return false;
    }
    if (stringify) {
      sessionStorage.setItem(key, JSON.stringify(data));
    } else {
      sessionStorage.setItem(key, data);
    }
    return true;
  }

  existSession():boolean {
    if(sessionStorage.getItem("currentEmployee")){
      return true;
    }
    return false;
  }

  cleanSession(){
    sessionStorage.clear();
  }


  private getData (key: string, removeItem: boolean = false): any {
    const data: any = sessionStorage.getItem(key);
    if (removeItem) {
      sessionStorage.removeItem(key);
    }
    return data;
  }

  getToken (): string {
    return this.getDataAsJSON("token") ;
  }

  getLogin (): string {
    return this.getDataAsJSON("login") ;
  }

  getName (): string {
    return this.getDataAsJSON("name") ;
  }

  getColor (): string {
    return this.getDataAsJSON("color") ;
  }

  getSigle (): string {
    return this.getDataAsJSON("sigle") ;
  }

  getRole (): string[] {
    return this.getDataAsJSON("roles") ;
  }


  exists(key: string): boolean {
    if (sessionStorage.getItem(key)) {
        return true;
    }
    return false;
  }

  existsAll(): boolean {
    if (
      sessionStorage.getItem("token") &&
      sessionStorage.getItem("roles") &&
      sessionStorage.getItem("login") &&
      sessionStorage.getItem("color") &&
      sessionStorage.getItem("sigle") &&
      sessionStorage.getItem("name")) {
        return true;
    }
    return false;
  }
/*
  getData (key: string, removeItem: boolean = false): any {
    const data: any = sessionStorage.getItem(key);
    if (removeItem) {
      sessionStorage.removeItem(key);
    }
    return data;
  }


*/
  removeData (key: string) {
    sessionStorage.removeItem(key);
  }

  removeAllData () {
    sessionStorage.clear();
  }

  removeDataToken () {
    localStorage.clear();
  }
}
