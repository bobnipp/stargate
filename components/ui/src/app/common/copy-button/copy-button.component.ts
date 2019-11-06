import {Component, Input, OnInit} from '@angular/core'

@Component({
    selector: 'stargate-copy-button',
    templateUrl: './copy-button.component.html',
    styleUrls: ['./copy-button.component.scss']
})
export class CopyButtonComponent {

    @Input()
    textToCopy: string

    isCopied: boolean = false

    constructor() {
    }

    copyText() {
        this.isCopied = true
        const fakeElem = document.createElement('textarea');
        // Prevent zooming on iOS
        fakeElem.style.fontSize = '12pt';
        // Reset box model
        fakeElem.style.border = '0';
        fakeElem.style.padding = '0';
        fakeElem.style.margin = '0';
        // Move element out of screen horizontally
        fakeElem.style.position = 'absolute';
        fakeElem.style[ 'right'] = '-9999px';
        // Move element to the same position vertically
        const yPosition = window.pageYOffset || document.documentElement.scrollTop;
        fakeElem.style.top = `${yPosition}px`;

        fakeElem.setAttribute('readonly', '');
        fakeElem.value = this.textToCopy;

        const activeElement = document.activeElement
        activeElement.appendChild(fakeElem)

        fakeElem.select()
        document.execCommand('copy')
        activeElement.removeChild(fakeElem)
        setTimeout(() => {
            this.isCopied = false
        }, 1000)
    }

}
