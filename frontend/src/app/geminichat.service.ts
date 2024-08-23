import { Injectable } from '@angular/core';
import { GoogleGenerativeAI } from '@google/generative-ai';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeminichatService {

  private generativeAi: GoogleGenerativeAI;

  private messageHistory: BehaviorSubject<any> = new BehaviorSubject(null);

  constructor() {
    this.generativeAi = new GoogleGenerativeAI('AIzaSyBCEdtPnTXDIf1Gz87Tw44vtLGsT38rA9U');    // Replaced with your own GEMINI Key
  }

  async generateText(prompt: string) {
    try {
      const model = this.generativeAi.getGenerativeModel({ model: 'gemini-pro'});
      this.messageHistory.next({
        from: 'user',
        message: prompt
      })

      const result = await model.generateContent(prompt);
      const response = await result.response;
      const text = response.text();
      this.messageHistory.next({
        from: 'bot',
        message: text
      })
    } catch (error:any) {
      if(error.toString().includes("Candidate was blocked due to SAFETY")) {
        this.messageHistory.next({
          from: 'bot',
          message: "Your prompt seems to violate our safety guidelines. Please try again with a different prompt."
        })
      }
    }
  }

  public getMessageHistory(): Observable<any> {
    return this.messageHistory.asObservable();
  }

  resetHistory() {
    this.messageHistory.next([]);
  }
}
