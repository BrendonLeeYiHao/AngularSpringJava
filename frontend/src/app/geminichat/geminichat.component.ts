import { Component, OnDestroy } from '@angular/core';
import { GeminichatService } from '../geminichat.service';

@Component({
  selector: 'app-geminichat',
  templateUrl: './geminichat.component.html',
  styleUrls: ['./geminichat.component.css']
})
export class GeminichatComponent implements OnDestroy {

  prompt: string = '';
  chatHistory: any[] = [];

  constructor(private geminiService: GeminichatService) {
    this.geminiService.getMessageHistory().subscribe((res) => {
      if(res) {
        this.chatHistory.push(res);
      }
    })
  }
  
  ngOnDestroy(): void {
    this.geminiService.resetHistory();
  }

  sendData() {
    if(this.prompt) {
      this.geminiService.generateText(this.prompt);
      this.prompt = "";
    }
  }

  formatText(text: string) {
    const result = text.replaceAll('*', '');
    return result;
  }
  
}
