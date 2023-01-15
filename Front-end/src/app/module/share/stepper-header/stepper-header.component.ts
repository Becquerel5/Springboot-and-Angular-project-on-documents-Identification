import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-stepper-header',
  templateUrl: './stepper-header.component.html',
  styleUrls: ['./stepper-header.component.scss']
})
export class StepperHeaderComponent implements OnInit {

  @Input()
  currentStep: number = 1;

  @Input()
  treatementStep: string[]=[];

  step=1;


  constructor() {
   }

  ngOnInit(): void {

  }

  private hasClass(elem: any, className: any) {
    return new RegExp(' ' + className + ' ').test(' ' + elem.className + ' ');
  }

  private addClass(elem: any, className: any) {
    if (!this.hasClass(elem, className)) {
        elem.className += ' ' + className;
    }
  }

  private removeClass(elem: any, className: any) {
    var newClass = ' ' + elem.className.replace( /[\t\r\n]/g, ' ') + ' ';
    if (this.hasClass(elem, className)) {
        while (newClass.indexOf(' ' + className + ' ') >= 0 ) {
            newClass = newClass.replace(' ' + className + ' ', ' ');
        }
        elem.className = newClass.replace(/^\s+|\s+$/g, '');
    }
  }

  private onSetStepper(){
    let stepper = document.getElementById('stepper1');
    let steps = stepper?.getElementsByClassName('step');
    Array?.from(steps!)?.forEach((step, index) => {
      let stepNum = index + 1;
      if (stepNum === this.currentStep) {
        this.addClass(step, 'editing');
      } else {
        this.removeClass(step, 'editing');
      }
      if (stepNum < this.currentStep) {
        this.addClass(step, 'done');
        this.removeClass(step, 'editing');
      } else {
        this.removeClass(step, 'done');
      }
    });
  }

}
